package spring.skeleton.web.v1;

import spring.skeleton.domain.message.Message;
import spring.skeleton.domain.message.MessageNotFoundException;
import spring.skeleton.domain.message.MessageRepository;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController("MessagesV1")
@RequestMapping("/v1/messages")
@Api(value = "Messages V1", tags = "Messages Version 1")
public class MessagesController {

    private MessageRepository repository = new MessageRepository();
    {
        repository.add(new Message(
                UUID.randomUUID().toString(),
                "Very first message"
        ));
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Message> getAll() {
        return repository.getAll()
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.GET)
    public Message get(@PathVariable String id) {
        return repository
                .get(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @RequestMapping(method= RequestMethod.POST)
    public Message add(@RequestBody @Validated Message message) {
        return repository.add(message);
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.PUT)
    public Message update(@PathVariable String id, @RequestBody Message message) {
        repository
                .get(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
        return repository.update(id, message);
    }

    @RequestMapping(path = "/{id}", method= RequestMethod.DELETE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Message delete(@PathVariable String id) {
        return repository.
                delete(id).
                orElseThrow(() -> new MessageNotFoundException(id));
    }

}
