package spring.skeleton.domain.message;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class MessageRepository {

	HashMap<String, Message> memory = new HashMap<>();

	public Message add(Message message) {
		if (message.getId() == null) {
			message.setId(UUID.randomUUID().toString());
		}
		memory.put(message.getId(), message);
		return message;
	}

	public Message update(String id, Message message) {
		message.setId(id);
		memory.put(id, message);
		return message;
	}

	public Optional<Message> get(String id) {
		return Optional.ofNullable(memory.get(id));
	}

	public Optional<Message> delete(String id) {
		return Optional.ofNullable(memory.remove(id));
	}

	public Stream<Message> getAll() {
		return memory
				.values()
				.stream();
	}
}
