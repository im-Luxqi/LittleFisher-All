import {Injectable} from "@angular/core";
/**
 * Created by F1 on 2017/10/16.
 */
@Injectable()
export class FisherConfig{
  public appConfig:any = {
    baseUrl:"/apis",
    name: 'Bigworld',
    version: '1.0.0',
  };
  private urls:any ={
    login:"/login"
  }
}
