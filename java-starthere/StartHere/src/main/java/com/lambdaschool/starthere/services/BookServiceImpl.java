package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepos;

    @Autowired
    private AuthorRepository authorrepos;

    @Override
    public List<Book> findAll(Pageable pageable)
    {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id)
    {
        return bookrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (bookrepos.findById(id).isPresent())
        {
            bookrepos.deleteById(id);
            // authorrepos.deleteBookAuthorsByBookId(id);
        }
        else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (book.getTitle() != null)
        {
            currentBook.setTitle(book.getTitle());
        }

        if (book.getIsbn() != null)
        {
            currentBook.setIsbn(book.getIsbn());
        }

        if (book.getCopy() != 0)
        {
            currentBook.setCopy(book.getCopy());
        }

        return bookrepos.save(currentBook);
    }
}

