// IBookManager.aidl
package com.solarexsoft.aidldemo;
import com.solarexsoft.aidldemo.IBook;

// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
