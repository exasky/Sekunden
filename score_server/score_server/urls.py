from django.conf.urls import patterns, include, url

from django.contrib import admin
from sekunden_app import views

admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'score_server.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    url(r'^admin/', include(admin.site.urls)),
    url(r'^ws-score/get_all', views.get_all, name='get_all'),
    url(r'^ws-score/add_score', views.add_score, name='add_score' ),
    #url(r'^ws-score/', ),
)
