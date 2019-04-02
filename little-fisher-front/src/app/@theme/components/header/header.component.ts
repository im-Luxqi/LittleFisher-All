import { Component, Input, OnInit } from '@angular/core';

import { NbMenuService, NbSidebarService } from '@nebular/theme';
import { UserData } from '../../../@core/data/users';
import { AnalyticsService } from '../../../@core/utils';
import { LayoutService } from '../../../@core/utils';
import { NbAuthService } from '@nebular/auth';
import { Router } from '@angular/router';
import { tap, take, map } from 'rxjs/operators';


@Component({
  selector: 'ngx-header',
  styleUrls: ['./header.component.scss'],
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {

  @Input() position = 'normal';

  user: any;

  userMenu = [{ title: 'Profile' }, { title: 'Log out' }];

  constructor(private sidebarService: NbSidebarService,
              private menuService: NbMenuService,
              private userService: UserData,
              private authService: NbAuthService,
              private router: Router,
              private analyticsService: AnalyticsService,
              private layoutService: LayoutService) {
  }

  ngOnInit() {



    this.userService.getUsers().subscribe(
        (users: any) =>{
          this.user = users.jz;
        }
    );

      this.menuService.onItemClick()
          .pipe(
            map((tag:any)=> tag.item.title))
          .subscribe(title=>{
            console.log(title);
            switch (title) {
              case "Profile":
                break;
              case "Settings":
                break;
              case "Log out":
                this.router.navigate(["auth/logout"]);
                break;
            }
          })
  }

  onContecxtItemSelection(title: any) {
    switch (title) {
      case "Profile":
        break;
      case "Settings":
        break;
      case "Log out":
      // console.log("qwer");s
        this.router.navigate["#/auth/logout"];
        // window.location.href = "/#/auth/logout";
        break;
    }
  }

  toggleSidebar(): boolean {
    this.sidebarService.toggle(true, 'menu-sidebar');
    this.layoutService.changeLayoutSize();

    return false;
  }

  goToHome() {
    this.menuService.navigateHome();
  }

  startSearch() {
    this.analyticsService.trackEvent('startSearch');
  }
}
