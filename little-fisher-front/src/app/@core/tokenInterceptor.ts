import { Injectable, Injector } from '@angular/core';
import {
    HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { NbAuthService, NbAuthToken, NbAuthJWTInterceptor } from '@nebular/auth';
import { switchMap, tap, finalize } from 'rxjs/operators';
import { error } from 'util';


/**
 * 为每个请求加上jwtToken
 */
@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    private authService: NbAuthService;

    constructor(private injector: Injector) { }
    intercept(req: HttpRequest<any>, next: HttpHandler):
        Observable<HttpEvent<any>> {
        this.authService = this.injector.get(NbAuthService);

        return this.authService.isAuthenticated()
            .pipe(
                switchMap(authenticated => {
                    console.log(authenticated);
                    if (authenticated) {
                        return this.authService.getToken().pipe(
                            switchMap((token: NbAuthToken) => {
                                const jwt = `Bearer ${token.getValue()}`;
                                req = req.clone({
                                    setHeaders: {
                                        Authorization: jwt,
                                    },
                                });
                                // return next.handle(req);
                                return this.nextOperation(req,next);
                            })
                        )
                    } else {
                       return this.nextOperation(req,next);
                    }
                }),
            )
    }

    nextOperation(req: HttpRequest<any>, next: HttpHandler){
        // extend server response observable with logging
        let ok: any;
        return next.handle(req)
        .pipe(
            tap(
                // Succeeds when there is a response; ignore other events
                event => ok = event instanceof HttpResponse ? event : null,
                // Operation failed; error is an HttpErrorResponse
                error => ok = null
              ),
        // Log when response observable either completes or errors
        finalize(() => {
            const started = Date.now();
            const elapsed = Date.now() - started;
            // const msg = `${req.method} "${req.urlWithParams}"
            // ${ok} in ${elapsed} ms.`;
            if(ok){
                console.log(ok);
            }

            // this.messenger.add(msg);
        })
        );
    }
}