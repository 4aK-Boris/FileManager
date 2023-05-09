# FileManager
Файловый менеджер

Стеке технологий:
-Kotlin
-Jetpack Compose
-Koin
-MVVM
-Room
-Coroutines

Внешний вид приложения: 
![image](https://github.com/4aK-Boris/FileManager/assets/112987759/20d7bbb3-0593-4182-895d-60fbc21a6b90)
![image](https://github.com/4aK-Boris/FileManager/assets/112987759/77dcd844-7547-464f-8c58-d5f8eb53e54f)

Немного о работе приложения:
Вся необходимая информация тображается, как требовалось. При каждом запуске приложения хэш каждого файла помещается в БД. Это работает через WorkManager, поэтому должно продолжать работу при сворачивании приложения. 
При нажатии на папку происходит переход в неё. При нажатии на файл появляется меню выбора приложения для открытия файла, если нет такого приложения, то появляется ошибка с соответствующим сообением. При долгом нажатии на файл будет предложено выбрать приложение с которым стоит им поделиться.
Над списком файлом есть строка, в которой указан путь к открытой папке. При нажатии на любую часть пути произойдёт переход в ней.
Сортировка файлов доступна на 4 критериям: по алфавиту, по размеру, по дате создания и по расширению. Также доступна сортировка в обратном порядке. Реализовано через выпадающее меню, кнопка расположена на TopBar. Для выбора другого порядка сортировки нужно нажать уже на выбранный критерий сортировки. Выбранный критерий и выбранный порядок указаны стрелочкой.
