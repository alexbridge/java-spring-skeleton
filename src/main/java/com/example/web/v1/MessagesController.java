package com.example.web.v1;

import com.example.domain.message.Message;
import com.example.domain.message.MessageNotFoundException;
import com.example.domain.message.MessageRepository;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("MessagesV1")
@RequestMapping("/v1/messages")
@Api(value = "Messages V1", tags = "Messages Version 1")
public class MessagesController {

    private MessageRepository repository = new MessageRepository();

    @RequestMapping(method= RequestMethod.GET)
    public List<Message> getAll() {
        return repository.getAll()
                .collect(Collectors.toList());
    }

    @RequestMapping(method= RequestMethod.POST)
    public Message add(@RequestBody @NotNull @Validated Message message) {
        return repository.add(message);
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.GET)
    public Message get(@PathVariable @NotNull String id) {
        return repository
                .get(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.PUT)
    public Message update(@PathVariable @NotNull String id, @RequestBody Message message) {
        repository
                .get(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
        return repository.update(id, message);
    }
}
