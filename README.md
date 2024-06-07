# HAOM

## Group Name : Hope
Nama Anggota :

  1. Andi Riswanda - H071231008
  2. Ilham Kurniawan - H071231024
  3. Nabil Muflif Malape - H071231036

## Tema yang dipilih : Social Impact and Volunteering
## Nama Pendamping : Kevin Leonardo
## Tim Juri :

  1. Ihlasul Mufti Faqih
  2. Muh. Aqsa Ramadhan

## Executive Summary
Dalam era digital saat ini, teknologi memiliki potensi besar untuk menggerakkan aksi kolektif. Namun, banyak aplikasi yang ada lebih fokus pada hiburan atau kebutuhan pribadi, meninggalkan potensi untuk memobilisasi aksi sosial belum sepenuhnya dimanfaatkan. Aplikasi ini memanfaatkan tren gamifikasi dan sosial media untuk menciptakan lingkungan yang mendukung dan memotivasi individu dalam berkontribusi terhadap kebaikan sosial. Proyek ini dipilih berdasarkan kebutuhan yang mendesak untuk meningkatkan partisipasi masyarakat dalam kegiatan sosial yang bermakna. Dengan menggunakan 
teknologi dan prinsip gamifikasi, aplikasi ini dapat menciptakan cara yang menarik dan interaktif untuk mendorong aksi sosial. Melalui fitur-fitur seperti tantangan yang dikategorikan, elemen gamifikasi, dan integrasi media sosial, aplikasi ini tidak hanya memfasilitasi tetapi juga memotivasi pengguna untuk terus berpartisipasi dan merasakan dampak positif dari tindakan mereka.

## Fitur Aplikasi
1. Login dan Register. 
2. Pengguna dapat memasang dan menghapus foto profil. 
3. Pengguna dapat membuat postingan baru dengan memilih genre, memasukkan 
   judul, deskripsi, dan mengunggah gambar.
4. Pengguna memperoleh poin jika membantu pengguna lain. 
5. Leaderboard. Dapat melihat poin setiap pengguna.

## Link Repository GitHub
Link Repository Github Project : https://github.com/AndiRiswanda/HAOM 

## Penjelasan Penerapan Prinsip OOP
1. Pada App.java 
Inheritance (Pewarisan): 
Kelas App merupakan subclass dari Application, yang mengatur siklus hidup aplikasi dan antarmuka pengguna. Ini menunjukkan penerapan inheritance dari kelas Application dan penggunaan metode override seperti start.  
Polymorphism (Polimorfisme): 
Metode start di App mengoverride metode start dari Application, menunjukkan penggunaan polymorphism. 
Encapsulation (Enkapsulasi): 
Kelas ini menggunakan atribut PrimaryStage yang hanya dapat diakses melalui metode publik yang disediakan oleh objek Stage. Atribut ini disembunyikan dan diatur menggunakan metode setter dan getter. 
Abstraction (Abstraksi): 
Mengatur dan mengelola berbagai scene dalam aplikasi seperti login dan scene utama tanpa mengungkapkan detail implementasi. Detail seperti pengaturan icon dan ukuran stage diabstraksikan dari logika aplikasi utama. 
2. Pada CustomAlert.java 
Inheritance (Pewarisan): 
Kelas CustomAlert adalah subclass dari Alert, yang memungkinkan pembuatan custom alert dengan styling khusus. 
Polymorphism (Polimorfisme):  
Metode applyCustomStyling dan setAlertIcon mengoverride dan menambahkan fungsionalitas pada metode yang ada di kelas Alert. 
Encapsulation (Enkapsulasi): 
Menyembunyikan detail implementasi dari styling dan ikon yang digunakan pada alert. 
3. Pada ImageLoader.java 
Encapsulation (Enkapsulasi): 
Menyembunyikan detail implementasi dari proses loading dan saving image, serta mengatur akses melalui metode publik. 
Abstraction (Abstraksi): 
Menyederhanakan penggunaan kelas untuk memuat dan menyimpan gambar tanpa menunjukkan detail implementasi. 
4. Pada LoginScene.java  
Encapsulation  (Enkapsulasi): 
Menyembunyikan Detail Implementasi Kelas LoginScene menyembunyikan detail implementasi dari antarmuka login dan hanya menampilkan metode publik showLoginScreen untuk menampilkan scene login. Semua detail pengelolaan UI disembunyikan di dalam metode ini. Atribut dan metode yang berhubungan dengan tampilan login seperti usernameLabel, passwordLabel, loginButton, dan showAlert diatur secara internal dalam metode showLoginScreen, sehingga pengguna kelas LoginScene tidak perlu mengetahui detail ini untuk menggunakan kelas tersebut. 
Abstraction (Abstraksi): 
Menyederhanakan Penggunaan: Kelas ini menyederhanakan interaksi pengguna dengan login tanpa menunjukkan detail bagaimana scene login diatur. Pengguna aplikasi hanya perlu memanggil metode publik showLoginScreen dengan Stage sebagai parameter. Kelas ini juga menyembunyikan detail bagaimana validasi login dilakukan dengan memanggil metode DatabaseConnection.validateLogin dan bagaimana 
scene utama ditampilkan dengan memanggil MainScene.showMainScreen. 
Polymorphism (Polimorfisme): 
Menggunakan Berbagai Bentuk: Metode showAlert digunakan untuk menampilkan pesan kepada pengguna, dengan menggunakan Alert dari JavaFX. Ini menunjukkan kemampuan metode yang sama untuk menampilkan berbagai jenis pesan kesalahan atau informasi berdasarkan konteks yang berbeda. Event handler yang digunakan pada berbagai elemen UI (seperti loginButton, registerLink, passwordField) juga menunjukkan 
polymorphism karena mereka mengimplementasikan interface yang sama namun dengan perilaku yang berbeda. 
Composition (Komposisi): 
Menggabungkan Berbagai Objek: Kelas LoginScene memanfaatkan komposisi dengan menggunakan berbagai komponen JavaFX seperti Label, TextField, PasswordField, Button, GridPane, VBox, StackPane, Circle, Rectangle, dan Text. Semua komponen ini digabungkan untuk membentuk antarmuka login yang kompleks. Objek-objek ini bekerja sama untuk membentuk UI yang lengkap, menunjukkan bagaimana kelas LoginScene bergantung pada banyak objek lain untuk fungsionalitasnya. 
5. Pada MainScene.java 
Inheritance (Pewarisan): 
BaseScene Class: Kelas abstrak BaseScene digunakan sebagai superclass untuk berbagai scene lainnya seperti ProfileScene dan LeaderBoard. Ini memungkinkan pewarisan atribut dan metode umum.  
Subclassing: ProfileScene dan LeaderBoard adalah subclass dari BaseScene, yang menunjukkan penggunaan inheritance untuk memperluas fungsionalitas kelas dasar.  
Polymorphism (Polimorfisme): 
Metode Show: Metode show di BaseScene dioverride oleh subclass seperti ProfileScene dan LeaderBoard untuk memberikan implementasi spesifik dari tampilan scene. 
Event Handling: Event handler yang digunakan untuk berbagai tombol menunjukkan polymorphism dengan mengimplementasikan aksi yang berbeda berdasarkan tombol yang diklik (misalnya, loginButton, logoutButton, btn1, btn2, btn3). 
Encapsulation (Enkapsulasi): 
Menyembunyikan Detail Implementasi: Detail implementasi dari tampilan utama dan profil disembunyikan dalam kelas MainScene, dan hanya metode publik yang diperlukan untuk menunjukkan scene tersebut yang diekspos. 
Atribut Privat: Atribut dan metode seperti username, profileImageView, logoutButton, dan lainnya diatur secara internal dalam metode showMainScreen dan show untuk menyembunyikan detail implementasi. 
Abstraction (Abstraksi): 
Menyederhanakan Penggunaan: Metode showMainScreen dan show menyederhanakan penggunaan dengan menyediakan cara yang mudah untuk menampilkan scene tanpa mengetahui detail implementasi dari bagaimana setiap elemen UI diatur. 
Metode Abstrak: BaseScene mendefinisikan metode abstrak show yang harus diimplementasikan oleh subclass, menunjukkan abstraksi dalam mendefinisikan antarmuka umum untuk semua scene. 
Composition (Komposisi): 
Menggabungkan Berbagai Objek: MainScene menggabungkan berbagai komponen UI seperti Button, Label, ImageView, StackPane, GridPane, dan VBox untuk membentuk antarmuka pengguna yang kompleks. 
Kerjasama Objek: Objek-objek ini bekerja sama untuk membentuk UI yang lengkap dan interaktif, menunjukkan bagaimana kelas MainScene bergantung pada banyak objek lain untuk fungsionalitasnya. 
6. Pada PostCreationScene.java 
Inheritance (Pewarisan): 
Kelas PostCreationScene adalah subclass dari BaseScene, yang mewarisi atribut dan metode umum dari kelas dasar. 
Polymorphism (Polimorfisme): 
Metode show di PostCreationScene mengoverride metode show dari BaseScene untuk memberikan implementasi spesifik dari tampilan scene pembuatan postingan. 
Encapsulation (Enkapsulasi): 
Atribut genresList diatur sebagai private dan hanya dapat diakses melalui metode publik di dalam kelas PostCreationScene. 
Abstraction (Abstraksi): 
Kelas ini menyederhanakan proses pembuatan postingan dengan menyediakan antarmuka pengguna yang intuitif tanpa menunjukkan detail implementasi bagaimana data diproses atau disimpan. 
7. Pada PostInfo.java 
Encapsulation (Enkapsulasi): 
Atribut seperti genre, title, description, dan imageURL diatur sebagai private dan hanya dapat diakses melalui metode getter. Ini menyembunyikan detail implementasi dan hanya memungkinkan akses melalui metode yang telah ditentukan. 
Abstraction (Abstraksi): 
Kelas ini menyederhanakan representasi data postingan dengan menyediakan cara yang terstruktur untuk mengakses dan mengubah data tanpa menunjukkan detail implementasi. 
8. Pada RegisterScene.java  
Encapsulation (Enkapsulasi): 
Menyembunyikan Detail Implementasi: Kelas RegisterScene menyembunyikan detail implementasi dari antarmuka registrasi dan hanya menampilkan metode public showRegisterScreen untuk menampilkan scene 
registrasi. Semua detail pengelolaan UI dan validasi input disembunyikan di dalam metode ini. 
Metode Validasi: Metode isUsernameValid, isEmailValid, dan isPasswordValid digunakan untuk memvalidasi input pengguna, menyembunyikan detail implementasi dari logika validasi dan hanya menampilkan antarmuka sederhana untuk pengecekan validitas. 
Abstraction (Abstraksi): 
Menyederhanakan Penggunaan: Kelas ini menyederhanakan interaksi pengguna dengan antarmuka registrasi tanpa menunjukkan detail bagaimana scene registrasi diatur. Pengguna aplikasi hanya perlu memanggil metode publik showRegisterScreen dengan Stage sebagai parameter. 
Abstraksi Proses Registrasi: Kelas ini menyembunyikan detail bagaimana validasi input dilakukan dan bagaimana data pengguna disimpan dengan memanggil metode UserController.registerUser. 
Polymorphism (Polimorfisme): 
Event Handling: Event handler yang digunakan untuk berbagai elemen UI (seperti registerButton, loginLink, exitButton) menunjukkan polymorphism dengan mengimplementasikan aksi yang berbeda berdasarkan elemen yang diklik. Metode registerButton.setOnAction menghandle aksi pendaftaran dengan memvalidasi input dan menyimpan data pengguna jika valid. 
Alert: Penggunaan CustomAlert.showCustomAlert menunjukkan polymorphism dengan menampilkan pesan yang berbeda berdasarkan hasil validasi dan pendaftaran.  
Composition (Komposisi):
Menggabungkan Berbagai Objek: Kelas RegisterScene memanfaatkan komposisi dengan menggunakan berbagai komponen JavaFX seperti Label, TextField, PasswordField, Button, GridPane, VBox, StackPane, Circle, Rectangle, dan Text. Semua komponen ini digabungkan untuk membentuk antarmuka registrasi yang kompleks. 
Kerjasama Objek: Objek-objek ini bekerja sama untuk membentuk UI yang lengkap, menunjukkan bagaimana kelas RegisterScene bergantung pada banyak objek lain untuk fungsionalitasnya.

## Screenshots
### *Login Scene*
![Login Scene](https://github.com/AndiRiswanda/HAOM/blob/master/readme/Login%20Scene.jpg)

### *Register Scene*
![Register Scene](https://github.com/AndiRiswanda/HAOM/blob/master/readme/RegisterScene.jpg)

### *Main Scene*
![Main Scene](https://github.com/AndiRiswanda/HAOM/blob/master/readme/Main%20Scene.jpg)

### *Create Post Scene*
![Create Post Scene](https://github.com/AndiRiswanda/HAOM/blob/master/readme/Create%20a%20Post%20Scene.jpg)

### *Leadeboard Scene*
![Leaderboard Scene](https://github.com/AndiRiswanda/HAOM/blob/master/readme/Leaderboard%20Scene.jpg)

### *Post Scene*
![Help Post Scene](https://github.com/AndiRiswanda/HAOM/blob/master/readme/Post%20Scene.jpg)

### *Profile User Scene*
![Profile Scene](https://github.com/AndiRiswanda/HAOM/blob/master/readme/Profile%20Scene.jpg)

### *Help Post Scene*
![Help Post Scene](https://github.com/AndiRiswanda/HAOM/blob/master/readme/HelpPost%20Scene.jpg)

## Pengujian Pada Aplikasi
![Table1](https://github.com/AndiRiswanda/HAOM/blob/master/readme/TablePengujian1.jpg)

![Table2](https://github.com/AndiRiswanda/HAOM/blob/master/readme/TablePengujian2.jpg)

![Table3](https://github.com/AndiRiswanda/HAOM/blob/master/readme/TablePengujian3.jpg)
