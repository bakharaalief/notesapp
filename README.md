# Notes App

## Video preview

[youtube](https://youtube.com/shorts/_lgGd3VfE60)

## Feature

- add notes
- delete notes
- view notes
- edit notes

## Project Structure

I use clean architecture for this app and divide the app into several folders

- [data](https://github.com/bakharaalief/notesapp/tree/main/app/src/main/java/com/bakhdev/notesapp/datas) - handle the data
- [domain](https://github.com/bakharaalief/notesapp/tree/main/app/src/main/java/com/bakhdev/notesapp/domain) - handle business logic of this app
- [di](https://github.com/bakharaalief/notesapp/tree/main/app/src/main/java/com/bakhdev/notesapp/di) - handle dependency Injection
- [presentation](https://github.com/bakharaalief/notesapp/tree/main/app/src/main/java/com/bakhdev/notesapp/presentation) - handle view
- [helper](https://github.com/bakharaalief/notesapp/tree/main/app/src/main/java/com/bakhdev/notesapp/helper) - handle tools for mapping

## Dependencies

- dagger-hit
- rxjava
- sqlite
- java
