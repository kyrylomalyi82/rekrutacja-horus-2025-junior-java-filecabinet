# FileCabinet – Recruitment Task

## Opis
Implementacja klasy `FileCabinet`, która zarządza strukturą folderów (`Folder` i `MultiFolder`) oraz udostępnia metody:
- `findFolderByName(String name)` – zwraca pierwszy folder o podanej nazwie,  
- `findFoldersBySize(String size)` – zwraca wszystkie foldery o podanym rozmiarze,  
- `count()` – zwraca całkowitą liczbę folderów w strukturze.  

Rozwiązanie zostało oparte na rekurencyjnym przeszukiwaniu drzewa folderów przy użyciu **Java Stream API**.  
Obsługiwane są zarówno zwykłe foldery, jak i foldery zagnieżdżone (`MultiFolder`).  

## Testy
Projekt zawiera testy jednostkowe weryfikujące poprawność metod:  
- wyszukiwanie po nazwie,  
- filtrowanie po rozmiarze,  
- zliczanie wszystkich elementów w strukturze.  

---

## English version

### Description
This project implements the `FileCabinet` class, which manages a folder structure (`Folder` and `MultiFolder`) and provides methods:
- `findFolderByName(String name)` – returns the first folder with the given name,  
- `findFoldersBySize(String size)` – returns all folders of a given size,  
- `count()` – returns the total number of folders in the structure.  

The solution is based on **recursive traversal** of the folder tree using **Java Stream API**.  
Both regular and nested folders (`MultiFolder`) are supported.  

### Tests
The project includes unit tests verifying:
- name-based search,  
- size-based filtering,  
- counting of all elements in the structure.  
