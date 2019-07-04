import { TestBed } from '@angular/core/testing';

import { HttpErrorHandlerService } from './http-error-handler.service';

describe('HttpErrorHandlerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HttpErrorHandlerService = TestBed.get(HttpErrorHandlerService);
    expect(service).toBeTruthy();
  });
});
