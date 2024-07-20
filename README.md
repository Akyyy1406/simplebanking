Veritabanı olarak gömülü şekilde H2 Database eklentisini kullandım.
Derlenip ayağa kalktığında default olarak 2 banka hesabı veritabanında ekli şekilde program açılmaktadır.
Yeni hesap ekleme, kredi ekleme, kredi çıkarma, fatura ödeme,maaş yatırma, hesap detayı getirme endpointlerini programın içerisine postman collection olarak ekledim.
kredi ekleme,çıkarma özelliklerini sisteme type bazlı olarak eklendi. 
Transaction olarak tek veritabanı tablosunda tutulmakta olup type bazlı ayrışıyorlar. 
Yani Fatura ödemede bir kredi çıkarma işlemi fakat type olarak "BILL" olarak kaydedilip aynı zamanda payee ve phoneNumber parametlerini almakta.
Bu nedenle kredi ekleme çıkarma işlemlerinde parametre olarak type gönderilmesini istedim. ("DEPOSIT", "WITHDRAWAL", "BILL","SALARY")




Task 1: Model test sınıfında eklendi ve çalıştırıldı.

BONUS Task 1: Account sınıfındaki post işlemini ise yeni bir özellik geldiğinde account sınıfını etkilemeyecek şekilde yapıldı. 
			  Yeni bir transaction ekleme veya çıkarma işleminde sadece transaction sınıflarında işlem yapılması yeterli.
			  
Task 2: Postman collection olarak eklendi. Direk olarak postmana import edilip kullanılabilir.
		Eklenen enpointler: Hesap ekleme,kredi ekleme,kredi çıkarma, hesap detay görüntüleme. Kredi ekleme ve çıkarma için alt türlerinde örnekleri eklendi. (Bill,Salary)