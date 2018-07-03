import { Score } from './../models/score';

export class ScoreManager {
	scores: Score[];


averageScore():number{
	console.log( "sdahaskjdhaskjdhaskjd");
	console.log((this.scores.map((s) => s.value)));
	console.log( (this.scores.map((s) => s.value).reduce((a, b) => a+b)));
	return (this.scores.map((s) => s.value).reduce((a, b) => a+b))/ this.scores.length
}

}