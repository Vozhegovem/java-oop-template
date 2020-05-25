package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[]{};

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        if (schoolBooks[schoolBooks.length - 1] != null) {
            return true;
        }
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] findBooks = new SchoolBook[]{};
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                findBooks = Arrays.copyOf(findBooks, findBooks.length + 1);
                findBooks[findBooks.length - 1] = book;
            }
        }
        return findBooks;
    }

    @Override
    public boolean removeByName(String name) {
        if (findByName(name).length == 0) {
            return false;
        }
        int size = schoolBooks.length;

        for (int i = 0; i < size; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                System.arraycopy(schoolBooks, i + 1, schoolBooks, i, schoolBooks.length - i - 1);
                schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
                --i;
            }
            if (schoolBooks.length == 0) {
                return true;
            }
        }
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
