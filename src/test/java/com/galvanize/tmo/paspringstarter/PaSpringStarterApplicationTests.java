package com.galvanize.tmo.paspringstarter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class PaSpringStarterApplicationTests {

	@Autowired
	MockMvc mockMvc;

	private final String postResponseBody = "{\n" +
			"    \"id\": 1,\n" +
			"    \"author\": \"Douglas Adams\",\n" +
			"    \"title\": \"The Hitchhiker's Guide to the Galaxy\",\n" +
			"    \"datePublished\": 1979\n" +
			"}";

	@Test
	void contextLoads() {
	}

	@Test
	void isHealthy() throws Exception {
		mockMvc.perform(get("/health"))
				.andExpect(status().isOk())
				.andExpect(content().string("UP"));
	}

	@Test
	void testGetAllBooks() throws Exception {
		mockMvc.perform(get("/api/books")).andExpect(status().isOk());
	}

	@Test
	void testRemoveAllBooks() throws Exception {
		mockMvc.perform(delete("/api/books")).andExpect(status().isNoContent());
	}

	@Test
	void testAddToLibrary() throws Exception {
		mockMvc.perform(post("/api/books")
				.content(asJsonString(new Book("Douglas Adams", "The Hitchhiker's Guide to the Galaxy", 1979)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(content().json(postResponseBody));

	}
	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
