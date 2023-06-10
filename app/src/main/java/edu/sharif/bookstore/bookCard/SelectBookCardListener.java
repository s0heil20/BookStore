package edu.sharif.bookstore.bookCard;

public interface SelectBookCardListener {
    void onItemClicked(BookCardItem bookCardItem);

    void onDeleteItemClicked(BookCardItem bookCardItem);
}
