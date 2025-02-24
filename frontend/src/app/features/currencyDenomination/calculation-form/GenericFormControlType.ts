import { FormControl } from '@angular/forms';

export type GenericFormControlType<T> = {
  [K in keyof T]: FormControl<T[K]>;
};
