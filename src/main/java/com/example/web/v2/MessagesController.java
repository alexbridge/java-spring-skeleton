package com.example.web.v2;

import com.example.domain.message.Message;
import com.example.domain.message.MessageNotFoundException;
import com.example.domain.message.MessageRepository;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("MessagesV2")
@RequestMapping("/v2/messages")
@Api(value = "Messages V2", tags = "Messages Version 2")
public class MessagesController {

    private MessageRepository repository = new MessageRepository();

    @RequestMapping(method= RequestMethod.GET)
    public List<Message> getAll() {
        return repository.getAll()
                .collect(Collectors.toList());
    }

    @RequestMapping(method= RequestMethod.POST)
    public Message add(@RequestBody @NotNull Message message) {
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

    @RequestMapping(path = "/{id}", method= RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull String id) {
        repository
                .get(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
        repository.delete(id);
    }

}
