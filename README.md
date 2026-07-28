# Student Sorter — приложение сортировки кастомных классов

Консольное Java-приложение, реализующее алгоритмы сортировки объектов класса **Студент** (номер группы, средний балл, номер зачётной книжки). Программа работает в цикле: пользователь выбирает способ заполнения массива (из файла / рандомно / вручную) и его длину, затем может сортировать данные разными стратегиями и полями, искать элементы, записывать результаты в файл и считать вхождения элемента в многопоточном режиме. Выход из программы — только через соответствующий пункт меню.

## Функциональность

**Основное задание:**
- Кастомный класс `Student` с паттерном **Builder**
- **Валидация** всех вводимых данных (в том числе загружаемых из файла)
- Выбор источника данных: файл / рандом / ручной ввод + выбор длины массива
- Паттерн **Стратегия** (применён дважды: стратегии сортировки `SortStrategy` и стратегии заполнения `DataFiller`)
- Собственные реализации сортировок (выбором и быстрая) — готовые реализации сортировки/поиска не используются
- Сортировка по всем трём полям через компараторы (включая комбинированную по всем полям сразу)
- Собственный бинарный поиск
- Программа выполняется в цикле, выход — только по выбору пользователя

**Дополнительные задания:**
1. Сортировка по числовому полю: объекты с чётными значениями номера зачётной книжки сортируются в натуральном порядке, с нечетными — остаются на исходных позициях (`EvenFieldSort`)
2. Запись отсортированных коллекций и найденных значений в файл в режиме **добавления** (`ResultWriter`, `StandardOpenOption.APPEND`)
3. Заполнение коллекций посредством **Stream API** (`Stream.generate`, `Files.lines`)
   - 3*: кастомная коллекция `StudentList` (динамический массив с итератором)
4. Многопоточный подсчёт количества вхождений элемента N в коллекцию с выводом результата в консоль (`OccurrenceCounter`, `ExecutorService` + `Future`)

**Тесты:** ручные тестовые классы без сторонних библиотек (`SimpleAssert` + `TestRunner`), 70 проверок, включая граничные случаи.

## Структура проекта

```
student-sorter/
├── README.md
├── .gitignore
├── data/
│   └── students.txt                  ← входные данные (формат: "группа;балл;зачётка")
├── output/
│   └── results.txt                   ← результаты работы (режим добавления)
└── src/
    ├── com/team/studentsorter/       ← основной код
    │   ├── Main.java                 ← точка входа
    │   ├── menu/
    │   │   └── ConsoleMenu.java      ← главный цикл, меню, склейка модулей
    │   ├── model/
    │   │   └── Student.java          ← класс Студент + Builder (валидация в build())
    │   ├── validation/
    │   │   └── StudentValidator.java ← диапазоны полей, validate() и isValid()
    │   ├── input/                    ← стратегии заполнения (паттерн Стратегия)
    │   │   ├── DataFiller.java       ← интерфейс: List<Student> fill(int size)
    │   │   ├── ManualDataFiller.java ← ручной ввод с повторным запросом при ошибке
    │   │   ├── RandomDataFiller.java ← генерация через Stream.generate().limit()
    │   │   └── FileDataFiller.java   ← Files.lines() → parse → filter(isValid)
    │   ├── collection/
    │   │   └── StudentList.java      ← кастомная коллекция (доп. 3*)
    │   ├── sort/                     ← стратегии сортировки (паттерн Стратегия)
    │   │   ├── SortStrategy.java     ← интерфейс: sort(List, Comparator)
    │   │   ├── SelectionSortStrategy.java ← сортировка выбором (своя)
    │   │   ├── QuickSortStrategy.java     ← быстрая сортировка (своя)
    │   │   ├── StudentComparators.java    ← компараторы по 3 полям + комбинированный
    │   │   └── EvenFieldSort.java         ← доп. 1: чётные сортируются, нечётные на месте
    │   ├── search/
    │   │   └── BinarySearch.java     ← свой бинарный поиск
    │   ├── io/
    │   │   └── ResultWriter.java     ← доп. 2: запись в файл (APPEND)
    │   └── threads/
    │       └── OccurrenceCounter.java← доп. 4: многопоточный подсчёт вхождений
    └── test/com/team/studentsorter/  ← тесты (без сторонних библиотек)
        ├── TestRunner.java           ← запуск всех тестов
        ├── SimpleAssert.java         ← собственные утверждения
        ├── StudentTest.java
        ├── ValidatorTest.java
        ├── DataFillerTest.java
        ├── SortTest.java
        ├── EvenFieldSortTest.java
        ├── BinarySearchTest.java
        ├── StudentListTest.java
        ├── ResultWriterTest.java
        └── OccurrenceCounterTest.java
```

## Декомпозиция

Проект разбит на независимые модули, соединённые через интерфейсы-контракты (`SortStrategy`, `DataFiller`) и класс `Student`. Благодаря этому модули разрабатывались параллельно разными участниками и собираются вместе только в `ConsoleMenu`.

```
                        ┌─────────────┐
                        │    Main     │
                        └──────┬──────┘
                               ▼
                        ┌─────────────┐
                        │ ConsoleMenu │  ← единственная точка сборки всех модулей
                        └──┬───┬───┬──┘
             ┌─────────────┘   │   └──────────────┐
             ▼                 ▼                  ▼
      ┌────────────┐   ┌──────────────┐   ┌───────────────┐
      │   input/   │   │    sort/     │   │ io/, threads/ │
      │ DataFiller │   │ SortStrategy │   │ collection/   │
      │ 3 реализац.│   │ 2 реализац.  │   │               │
      └─────┬──────┘   │ + компараторы│   └───────┬───────┘
            │          │ EvenFieldSort│           │
            ▼          └──────┬───────┘           │
   ┌────────────────┐         │                   │
   │  validation/   │◄────────┴───────────────────┘
   │    model/      │   все модули работают только
   │ Student+Builder│   через модель и валидацию
   └────────────────┘
```

**Поток данных:** `input` (создание данных) → `model`/`validation` (гарантия корректности) → `sort`/`search` (обработка) → `io` (сохранение) / `threads` (анализ).

## Распределение задач в команде

| Участник | Ветка | Модули | Тесты |
|---|---|---|---|
| **Костя** (тим-лид) | `feature/model-menu` | `Student` + Builder, `ConsoleMenu`, `Main`, README, интеграция и мержи | `StudentTest` + интеграционный прогон |
| **Максим** | `feature/input` | `StudentValidator`, `ManualDataFiller`, `RandomDataFiller`, `FileDataFiller` (доп. 3) | `ValidatorTest`, `DataFillerTest` |
| **Шамиль** | `feature/sort` | `SelectionSortStrategy`, `QuickSortStrategy`, `StudentComparators`, `BinarySearch`, `EvenFieldSort` (доп. 1) | `SortTest`, `EvenFieldSortTest`, `BinarySearchTest` |
| **Аркадий** | `feature/extras` | `ResultWriter` (доп. 2), `StudentList` (доп. 3*), `OccurrenceCounter` (доп. 4) | `ResultWriterTest`, `StudentListTest`, `OccurrenceCounterTest` |

## Сборка и запуск

Требуется JDK 17+.

```bash
# компиляция
javac -d out src/com/team/studentsorter/*.java src/com/team/studentsorter/*/*.java 

# запуск приложения
java -cp out com.team.studentsorter.Main

# запуск тестов
javac -d out src/test/com/team/studentsorter/*.java -cp out
java -cp out com.team.studentsorter.TestRunner
```

Либо открыть папку проекта в IntelliJ IDEA, пометить `src` как Sources Root и запустить `Main` / `TestRunner`.

## Формат файла данных

`data/students.txt` — по одному студенту на строку, поля через `;`:

```
101;4.5;100234
102;3.8;100237
```

Строки, не проходящие валидацию, отбрасываются (в консоль выводится количество отброшенных строк).

**Правила валидации:**
- номер группы: 1–999
- средний балл: 2.0–5.0
- номер зачётной книжки: 100000–999999

## Git-воркфлоу

Каждый участник работает в своей ветке (`feature/model-menu`, `feature/input`, `feature/sort`, `feature/extras`). После завершения и ревью ветки последовательно мержатся в `main` с `--no-ff`, чтобы в истории сохранились merge-коммиты.
