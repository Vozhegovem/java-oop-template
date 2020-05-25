package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[]{};

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        authors = Arrays.copyOf(authors, authors.length + 1);
        authors[authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        Author author = Arrays.stream(authors)
                .filter(a -> {
                    if (a.getName().equals(name) && a.getLastName().equals(lastname)) {
                        return true;
                    }
                    return false;
                })
                .findFirst()
                .orElse(null);
        return author;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].equals(author)) {
                System.arraycopy(authors, i + 1, authors, i, authors.length - i - 1);
                authors = Arrays.copyOf(authors, authors.length - 1);
                break;
            }
        }
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
