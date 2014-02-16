from django.core.context_processors import request
from django.http.response import HttpResponse
from json import dumps
from sekunden_app.models import Scores


def get_all(request):
    """
        return all score as json param
        [order] : possible value :{author, score, date}
            to order by desc, just write '-score' instead of 'score'
    """
    if request.GET.get('order') != None:
        all = Scores.objects.order_by(request.GET.get('order'))
    else:
        all = Scores.objects.all()

    lst=list()
    for score in all:
        obj = dict()
        obj['author'] = score.author
        obj['score'] = str(score.score)
        obj['date'] = str(score.date)
        lst.append(obj)

    json = dumps(lst)
    return HttpResponse(json)



def add_score(request):
    """
        adding a score in DB: with get params :
        author : name of author (max-lenght = 10 )
        score : integer value
    """
    author = request.GET.get('author')
    score = int(request.GET.get('score'))

    row = Scores(score=score, author=author)
    row.save()

    return HttpResponse(row.id)
