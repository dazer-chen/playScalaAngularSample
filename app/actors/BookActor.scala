package actors

import akka.actor.Actor
import models.{BookReduced, Book}

case class SaveBook(book: Book)
case object ListBooks
case object ListBooksReduced
case class Books(books: List[Book])
case class BooksReduced(books: List[BookReduced])
case class Status(status: String, message: String)

class BookActor extends Actor {

  def receive = {
    case ListBooks          => sender ! Books(Book.list)
    case ListBooksReduced   => sender ! BooksReduced(Book.listReduced)
    case SaveBook(book)     =>
      Book.save(book)
      sender ! Status("OK", s"Book ${book.title} has been saved.")
  }

}
