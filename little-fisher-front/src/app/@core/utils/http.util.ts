import { FisherConfig } from './../../app-config';
import {Injectable} from "@angular/core";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { NbAuthService } from '@nebular/auth';



@Injectable({
  providedIn: 'root',
})
export class HttpUtil{
  private baseUrl:string;
  constructor(private config:FisherConfig, 
              private http: HttpClient){
    let app = config.appConfig;
    this.baseUrl = app.baseUrl;
  }

  post(url:string, param?:any):Observable<any> {
    return this.http.post(this.baseUrl + url,param);
  }

  get(url:string, param?:any):Observable<any> {
    return this.http.get(this.baseUrl + url,param);
  }


}
