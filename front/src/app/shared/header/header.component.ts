import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  menuToggled: Boolean = false;

  @HostListener('click', ['$event'])
  private onClick(event: Event) {
    const target = event.target as HTMLElement;
    if (target.getAttribute('aria-expanded')) {
      this.menuToggled = false;
    }
  }

  @HostListener('window:resize', ['$event'])
  private onResize(event: Event) {
    const target = event.target as Window;
    if (target.innerWidth > 768 && this.menuToggled) {
      this.menuToggled = false;
    } else {
      this.menuToggled = false;
    }
  }
}
