Projek ini dalam development untuk keperluan kebutuhan teknikal test/edukasi. Beberapa fitur pada mini project ini belum sepenuhnya berjalan atau belum selesai dikembangkan khusus nya bagian belum terbaca nya driver postgres ketika dijalankan
karena adanya keterbatasan waktu dan kesibukan di pekerjaan utama saat proses pembuatan. Namun sebagian lain dari segi teknis dalam bisnis sudah disiapkan untuk menggambarkan pendekatan dan kebutuhan microservices.

Mini Projek ini mengenai sebagian dari struktur sistem transaksi bank yang terdiri dari 3 modul yang nanti nya akan dibuat laporan keuangan yang diambil dari sebagian kerjaan yang saya kerjakan di projek perusahaan saya
dan dilakuan modifikasi sesuai dengan ketentuan dari perusahaan. Tahap Selanjut nya ketika 3 modul ini selesai diinput akan dibuat laporan transaksi bank dimana tiap hari nya akan 
dihitung berdasarkan modul Bank Transaction Beginning Balance tiap akun bank yang terdaftar dan ada fitur untuk penginputan data transaksi bank dalam jumlah banyak melalui upload excel (modul report dan upload belum termasuk dalam sini).

Fitur Utama
- CRUD transaksi setiap modul awal (beginning balance) bank, Bank Transaction dan Bank Transactio9n Beginning Balance

- Validasi menggunakan jakarta.validation dan Lombok 

- Query (native SQL) menggunakan @SubSelect dan filter untuk data yang dipilih menggunakan predicates

- Penerapan Spring IoC dan Java Stream

- Containerized dengan Docker

Teknologi yang Digunakan
 - Java 17 (menyesuaikan aplikasi yang dipakai sekarang)
 - Spring Boot
 - Spring Data JPA
 - PostgreSQL
 - Lombok
 - Swagger/OpenAPI (dokumentasi API)
 - Docker (untuk kontainerisasi)
 - Maven
