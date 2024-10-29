import { Component } from '@angular/core';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent {
  title = 'Autor';
  text = 'Projekat iz predmeta Razvoj Višeslojnih Aplikacija, na Fakultetu tehničkih nauka.';
  imageUrl = 'https://storage.najstudent.com/news/2030/56b5fea84166d6d1446ff1e5f9a654388d5bfddca791d9aaac4b1318321e9132.jpg';
  newText = 'Fakultet tehničkih nauka je visokoobrazovna ustanova smeštena u Novom Sadu i deo je Univerziteta u Novom Sadu. Fakultet je osnovan 18. maja 1960, a danas je sa 1.244 zaposlenih i preko 16.000 studenata jedan od najvećih fakulteta u regionu.  Lokacija : Trg Dositeja Obradovića br. 6, 21102 Novi Sad';
  showText = false;

  toggleText() {
    this.showText = !this.showText;
  }
}
