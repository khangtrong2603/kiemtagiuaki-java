package com.hutech.demo.controller;


import com.hutech.demo.entity.Book;
import com.hutech.demo.services.BookService;
import com.hutech.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategorys());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategorys());
            return "book/add";
        }
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.getAllCategorys());
        return "book/edit";
    }

    // Process the form submission for an updated book
    @PostMapping("/edit/{id}")
    public String updateBook(@ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book/edit";
        }
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}

