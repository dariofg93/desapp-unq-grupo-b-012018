class Language{
	private i18nValue: string;

	constructor() {
  	var search = window.location.search
    this.i18nValue = search.indexOf('language=') !== -1? 
    	search.substring(search.indexOf("language=")+9, search.indexOf("language=") +14): 
    	'es-AR';
  }

  public getI18nValue(): string{
    return this.i18nValue;
  }
}

export var LANGUAGE = {
  i18n: new Language(),

  getI18n(): string{
  	return this.i18n.getI18nValue();
  }
};