package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.NewsNameRecord;
import JOOQ.tables.records.NewsRecord;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.NewsNameService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.NewsService;

import java.util.ArrayList;
import java.util.List;

public class NewsController {
    private final NewsService newsService = new NewsService();
    private final NewsNameService newsNameService = new NewsNameService();
    private final LanguagesService languagesService = new LanguagesService();

    public NewsController() {}

    private List<NewsRepresentation> LoadData()
    {
        List<NewsRepresentation> newsRepresentations = new ArrayList<>();

        NewsNameRecord newsNameRecord;

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        int id = languagesService.GetIdByName(name);

        List<NewsRecord> newsRecords = newsService.getConfigs();

        for(NewsRecord rec : newsRecords){
            newsNameRecord = newsNameService.GetByIdAndLanguage(rec.getIdNews(),id);

            if(newsNameRecord != null){
                newsRepresentations.add(new NewsRepresentation());
                newsRepresentations.get(newsRepresentations.size()-1).Load(rec, newsNameRecord);
            }
        }

        return newsRepresentations;
    }

    public void Insert(NewsRepresentation insert){
        NewsRecord newsRecord = insert.getNews();
        NewsNameRecord newsNameRecord = insert.getNewsName();

        int newID = newsService.InsertAndReturn(newsRecord);
        newsNameRecord.setIdNews(newID);
        newsNameService.InsertNewsNameService(newsNameRecord);
    }

    public void InsertMultilingual(List<NewsRepresentation> insert){
        NewsRecord newsRecord = insert.get(0).getNews();
        int newId = newsService.InsertAndReturn(newsRecord);

        for(NewsRepresentation rep : insert){
            NewsNameRecord tmp = rep.getNewsName();
            tmp.setIdNews(newId);
            newsNameService.InsertNewsNameService(tmp);
        }
    }

    public void Update(NewsRepresentation update){
        NewsRecord newsRecord = update.getNews();
        NewsNameRecord newsNameRecord = update.getNewsName();

        newsService.UpdateNewsService(newsRecord);
        newsNameService.UpdateNewsNameService(newsNameRecord);
    }

    public void Delete(NewsRepresentation delete){
        NewsRecord newsRecord = delete.getNews();

        newsNameService.DeleteByNewsId(newsRecord.getIdNews());
        newsService.DeleteNewsService(newsRecord);
    }

    public List<NewsRepresentation> getItems(){
        return LoadData();
    }
}
