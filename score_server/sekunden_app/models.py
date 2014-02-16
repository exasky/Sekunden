from django.db import models

# Create your models here.
class Scores (models.Model):
    author = models.CharField(max_length=10,blank=True)
    score = models.IntegerField(blank=True)
    date = models.DateTimeField(auto_now=True,blank=True)

    def __str__(self):
        return self.author+" made  a score of "+ str(self.score) + " on the " + str(self.date)

    def __unicode__(self):
        return self.author+" made  a score of "+ str(self.score)+ " on the " + str(self.date)
