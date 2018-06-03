class Language{
  private i18nValue: string;

  constructor() {
    this.i18nValue = localStorage.getItem('language')? 
      localStorage.getItem('language'): 
      'en-US';
  }

  public getI18nValue(): string{
    return this.i18nValue;
  }
}

export var VARIABLES = {
  i18n: new Language(),

  getI18n(): string{
    return this.i18n.getI18nValue();
  }
};