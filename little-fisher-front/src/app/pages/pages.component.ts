import { Component, OnInit, OnDestroy } from '@angular/core';

import { MENU_ITEMS } from './pages-menu';
import { FisherAccountService } from '../@core/service/fisher.account.service.';
import { FisherAccount } from '../@core/entity/fisher.account';
import { NbMenuItem } from '@nebular/theme';

@Component({
  selector: 'ngx-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <ngx-sample-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-sample-layout>
  `,
})
export class PagesComponent implements OnInit, OnDestroy {

  private menu: any;
  private menuSubscription: any;

  constructor(private fisherAccountService: FisherAccountService) {}
  ngOnInit() {
    this.menuSubscription = this.fisherAccountService.getMenu()
      .subscribe((data: NbMenuItem[]) => {
        this.menu = [];
        if (data) console.log(data);
      });
  }
  ngOnDestroy() {
    // this.menuSubscription.unsubscribe();
  }


}
