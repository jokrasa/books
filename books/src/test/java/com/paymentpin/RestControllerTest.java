package com.paymentpin;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.paymentpin.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BookApplication.class)
@WebAppConfiguration
public class RestControllerTest {


	public BookRepository MockBookRepo = org.mockito.Mockito.mock(BookRepository.class);

	@Autowired
	public BookService MockBs;

	private MockMvc mockMvc;


	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new BookController(MockBookRepo, MockBs)).build();

	}

	@Test
	public void testHome() throws Exception {
		this.mockMvc.perform(get("/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(status().isOk());
	}

	@Test
	public void testAddBook() throws Exception {
		Book b = new Book();
		when(MockBookRepo.save(b)).thenReturn(b);

	}

	@Test
	public void testUpdateBook() throws Exception {
		Book b = new Book();
		b.setId(1);

		when(MockBookRepo.save(b)).thenReturn(b);

	}

	@Test
	public void testDeleteBook() throws Exception {
		Book b = new Book();
		b.setId(1);
		when(MockBookRepo.save(b)).thenReturn(b);
		MockBookRepo.delete(b);
		when(MockBookRepo.findById(1)).thenReturn(null);

	}

	@Test
	public void contextLoads() {
	}
}
