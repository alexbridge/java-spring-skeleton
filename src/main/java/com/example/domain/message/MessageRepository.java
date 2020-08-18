package com.example.domain.message;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class MessageRepository {

	HashMap<String, Message> memory = new HashMap<>();

	public Message add(Message message) {
		message.setId(String.valueOf(message.hashCode()));
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

	public void delete(String id) {
		memory.remove(id);
	}

	public Stream<Message> getAll() {
		return memory
				.values()
				.stream();
	}
}
