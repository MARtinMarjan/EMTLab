import axios from "../custom-axios/axios";

const LibraryService = {
    getBooks: () => {
        return axios.get("/books");
    },

    getBookById: (id) => {
        return axios.get(`/books/${id}`);
    },

    getCategories: () => {
        return axios.get("/categories");
    },

    getAuthors: () => {
        return axios.get("/authors");
    },

    getCountries: () => {
        return axios.get("/countries");
    },


    addBook: (name, category, author, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },

    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },

    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },

    markBookAsTaken: (id) => {
        return axios.put(`/books/marked/${id}`);
    }
}

export default LibraryService