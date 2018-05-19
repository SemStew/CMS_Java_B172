package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.NewsNameRecord;
import JOOQ.tables.records.NewsRecord;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.NewsNameService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.NewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class NewsController {
    /**
     * News management
     */
    private final NewsService newsService = new NewsService();
    /**
     * NewsName management
     */
    private final NewsNameService newsNameService = new NewsNameService();
    /**
     * Language management
     */
    private final LanguagesService languagesService = new LanguagesService();

    /**
     * NewsController constructor
     */
    public NewsController() {}

    /**
     * Get NewsRepresentation in selected language
     *
     * Use {@link #LoadData()} to get NewsRepresentations in selected language from database
     *
     * @return list of NewsRepresentation in selected language
     */
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

    /**
     * Insert new NewsRepresentation in single language
     *
     * Use {@link #Insert(NewsRepresentation)} to insert given NewsRepresentation in single language
     *
     * @param insert NewsRepresentation to be inserted
     */
    public void Insert(NewsRepresentation insert){
        NewsRecord newsRecord = insert.getNews();
        NewsNameRecord newsNameRecord = insert.getNewsName();

        int newID = newsService.InsertAndReturn(newsRecord);
        newsNameRecord.setIdNews(newID);
        newsNameService.InsertNewsNameService(newsNameRecord);
    }

    /**
     * Insert new NewsRepresentation in multiple languages
     *
     * Use {@link #InsertMultilingual(List)} to insert given NewsRepresentation in multiple languages
     *
     * @param insert NewsRepresentations to be inserted
     */
    public void InsertMultilingual(List<NewsRepresentation> insert){
        NewsRecord newsRecord = insert.get(0).getNews();
        int newId = newsService.InsertAndReturn(newsRecord);

        for(NewsRepresentation rep : insert){
            NewsNameRecord tmp = rep.getNewsName();
            tmp.setIdNews(newId);
            newsNameService.InsertNewsNameService(tmp);
        }
    }

    /**
     * Update NewsRepresentation
     *
     * Use {@link #Update(NewsRepresentation)} to update given NewsRepresentation
     *
     * @param update NewsRepresentation to be updated
     */
    public void Update(NewsRepresentation update){
        NewsRecord newsRecord = update.getNews();
        NewsNameRecord newsNameRecord = update.getNewsName();

        newsService.UpdateNewsService(newsRecord);
        newsNameService.UpdateNewsNameService(newsNameRecord);
    }

    /**
     * Delete NewsRepresentation
     *
     * Use {@link #Delete(NewsRepresentation)} to delete NewsRepresentation
     *
     * @param delete NewsRepresentation to be deleted
     */
    public void Delete(NewsRepresentation delete){
        NewsRecord newsRecord = delete.getNews();

        newsNameService.DeleteByNewsId(newsRecord.getIdNews());
        newsService.DeleteNewsService(newsRecord);
    }

    /**
     * Get all NewsRepresentation in selected language
     *
     * Use {@link #getItems()} to get all NewsRepresentation in selected language from database
     *
     * @return list of NewsRepresentation in selected language
     */
    public List<NewsRepresentation> getItems(){
        return LoadData();
    }
}
