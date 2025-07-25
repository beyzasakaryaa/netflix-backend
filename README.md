# Netflix Backend Projesi

Bu proje, Spring Boot ve PostgreSQL kullanılarak geliştirilmiş bir **Netflix benzeri içerik yönetim sistemidir**. 

## Özellikler

- **JWT ile kimlik doğrulama:** Kullanıcı kayıt ve giriş işlemleri
- **Rol bazlı yetkilendirme:** ADMIN ve USER rolleriyle farklı erişim seviyeleri
- **İçerik yönetimi:** Adminler için film/dizi ekleme, güncelleme, silme
- **Kullanıcı özellikleri:**
  - İçerik listeleme ve filtreleme (isim, tür, tarih vs.)
  - Favorilere içerik ekleme/çıkarma
  - Puan ve yorum bırakabilme
  - Kendi profilini görüntüleyebilme
- **Güvenlik:** Spring Security ile endpoint koruması, BCrypt şifreleme
- **CustomUserDetails:** Özelleştirilmiş kullanıcı yönetimi ve kimlik doğrulama
- **Veritabanı ilişkileri:** Kullanıcı-rol, içerik-tür, kullanıcı-favori içerik ilişkileri
- **Postman ile API testleri:** Swagger UI yerine, Postman koleksiyonu ile tüm API uç noktaları test edilmiştir
- **Hata yönetimi:** Anlaşılır hata mesajları ve global exception handling

## Kullanılan Teknolojiler

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **JWT (JSON Web Token)**
- **Maven**
- **Lombok**


## Notlar

- Proje Swagger UI ile gelmez, Postman veya başka bir API test aracı ile istek gönderebilirsiniz.
- Token tabanlı kimlik doğrulama ile güvenlik sağlanır.
- Her endpoint için rol kontrolü yapılmıştır.

## Katkıda Bulunanlar

- [beyzasakaryaa](https://github.com/beyzasakaryaa)

---

Daha fazla detay veya eklemek istediğin başlıklar varsa iletebilirsin!
