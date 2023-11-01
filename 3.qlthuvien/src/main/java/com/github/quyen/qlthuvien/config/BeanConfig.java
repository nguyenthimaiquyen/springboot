package com.github.quyen.qlthuvien.config;

import com.github.quyen.qlthuvien.entity.Book;
import com.github.quyen.qlthuvien.entity.BookBorrow;
import com.github.quyen.qlthuvien.entity.BooksDetail;
import com.github.quyen.qlthuvien.entity.Reader;
import com.github.quyen.qlthuvien.logic.BookBorrowLogic;
import com.github.quyen.qlthuvien.logic.BookLogic;
import com.github.quyen.qlthuvien.logic.MenuLogic;
import com.github.quyen.qlthuvien.logic.ReaderLogic;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {
//    @Bean
//    public MenuLogic menuLogic() {
//        return new MenuLogic(bookLogic(), readerLogic(), bookBorrowLogic());
//    }
//
//    @Bean
//    public BookLogic bookLogic() {
//        return new BookLogic();
//    }
//
//    @Bean
//    public ReaderLogic readerLogic() {
//        return new ReaderLogic();
//    }
//
//    @Bean
//    public BookBorrowLogic bookBorrowLogic() {
//        return new BookBorrowLogic(bookLogic(), readerLogic());
//    }

//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public Book book() {
//        return new Book();
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public Reader reader() {
//        return new Reader();
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public BookBorrow bookBorrow() {
//        return new BookBorrow();
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public BooksDetail booksDetail() {
//        return new BooksDetail();
//    }


}
