export interface Sector {
  id: number;
  name: string;
  parent?: Sector;
  children?: Sector[];
  level?: number;
}

export interface UserFormData {
  name: string;
  sectorIds: number[];
  agreeToTerms: boolean;
  editToken?: string;
}

export interface ApiResponse<T> {
  data?: T;
  error?: string;
}
