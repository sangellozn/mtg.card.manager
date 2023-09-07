import { Component } from '@angular/core';
import { UserInfo } from '../beans/user-info';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  userInfos: UserInfo[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUserInfos().subscribe(userInfos => this.userInfos = userInfos);
  }

}
