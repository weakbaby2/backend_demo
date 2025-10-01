Untuk Pertanyaan Tambahan:

1. Untuk Debit credit bersifat atomic dapet menggunakan anotation di SpringBoot yaitu @Modififying dan @Query di repository yang berperan untuk memastikan saldo yang terakhir

2. potensi race condition yang terjadi adalah adanya suatu transaksi yang berjumlah lebih dari 1 secara bersamaan. Sebagai contoh adalah ketika terdapat lebih dari 1 user yang mengakses pengecekan saldo di saat bersamaan dan melakukan transaksi saat bersamaan. Cara mengatasi nya :
-bisa menggunakan atomic query dengan anotasi di atas yaitu mengupdate dan perhitungan saldo langsung di DB dilakukan di awal ketika user mengakses pengecekan saldo.
- menambah kolom version di tabel user sehingga dapat menghindari data yang sama dapat diupdate secara bersamaan ketika version sudah berubah.

3. Jika ada kegagalan/error ketika melakukan transaksi dalam case ini saya menggunakan anotation @Transactional dalam SpringBoot yang berfungsi sebagai rollback secara keseluruhan ketika di tengah proses terdapat suatu kegagalan/exception.