package baseline.version3.springboot.pageAdmin;

import baseline.version3.springboot.pageAdmin.page.domain.pageAuthority.PageAuthorityMapper;
import baseline.version3.springboot.pageAdmin.page.domain.pageAuthority.PageAuthorityRequest;
import baseline.version3.springboot.pageAdmin.page.domain.parentPage.ParentPageMapper;
import baseline.version3.springboot.pageAdmin.page.domain.parentPage.ParentPageRequest;
import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageMapper;
import baseline.version3.springboot.pageAdmin.page.domain.subPage.SubPageRequest;
import baseline.version3.springboot.pageAdmin.page.repository.PageAuthorityRepository;
import baseline.version3.springboot.pageAdmin.page.repository.ParentPageRepository;
import baseline.version3.springboot.pageAdmin.page.repository.SubPageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "spring.jpa.hibernate.ddl-auto", havingValue = "create", matchIfMissing = false)
public class PageAdminInitializer implements InitializingBean {

    private final PageAuthorityMapper pageAuthorityMapper;
    private final ParentPageMapper parentPageMapper;
    private final SubPageMapper subPageMapper;

    private final PageAuthorityRepository pageAuthorityRepository;
    private final ParentPageRepository parentPageRepository;
    private final SubPageRepository subPageRepository;

    private String ROLE_ADMIN = "ADMIN";
    @Override
    public void afterPropertiesSet() throws Exception {
        makePageAuthority();

        makePageAdmin();

        makeMainPage();

        makeErrorPage();
    }

    private void makeErrorPage() {
        ParentPageRequest.RequestInsert errorParentPage = new ParentPageRequest.RequestInsert();
        errorParentPage.setParentPageRootPath("/error");
        errorParentPage.setParentPageTitle("Error");
        errorParentPage.setParentPageDescription("");
        Long parentPageId = saveParentPage(errorParentPage);

        SubPageRequest.RequestInsert errorPage = new SubPageRequest.RequestInsert();
        errorPage.setParentPageId(parentPageId);
        errorPage.setSubPagePath("/");
        errorPage.setSubPageTitle("Error");
        saveSubPage(errorPage);
    }

    private void makePageAdmin() {
        ParentPageRequest.RequestInsert pageAdmin = new ParentPageRequest.RequestInsert();
        pageAdmin.setParentPageRootPath("/page-admin");
        pageAdmin.setPageAuthorityCode(ROLE_ADMIN);
        pageAdmin.setParentPageTitle("페이지 어드민");
        pageAdmin.setParentPageDescription("페이지 권한 관리");
        Long parentPageId = saveParentPage(pageAdmin);

        SubPageRequest.RequestInsert indexPage = new SubPageRequest.RequestInsert();
        indexPage.setParentPageId(parentPageId);
        indexPage.setSubPagePath("/index");
        indexPage.setSubPageTitle("페이지 어드민 메인");
        indexPage.setSubPageDescription("메인");
        saveSubPage(indexPage);

        SubPageRequest.RequestInsert listPage = new SubPageRequest.RequestInsert();
        listPage.setParentPageId(parentPageId);
        listPage.setSubPagePath("/page/list");
        listPage.setSubPageTitle("페이지 리스트");
        listPage.setSubPageDescription("현재 관리 중인 페이지 목록을 보여줍니다.");
        saveSubPage(listPage);

        SubPageRequest.RequestInsert authListPage = new SubPageRequest.RequestInsert();
        authListPage.setParentPageId(parentPageId);
        authListPage.setSubPagePath("/page/auth/list");
        authListPage.setSubPageTitle("페이지 권한 등록");
        authListPage.setSubPageDescription("페이지 권한을 등록하고, 수정할 수 있습니다.");
        saveSubPage(authListPage);

        SubPageRequest.RequestInsert ipAccessListPage = new SubPageRequest.RequestInsert();
        ipAccessListPage.setParentPageId(parentPageId);
        ipAccessListPage.setSubPagePath("/ip-access/list");
        ipAccessListPage.setSubPageTitle("IP 차단 제어");
        ipAccessListPage.setSubPageDescription("차단할 IP 주소를 등록하고, 수정할 수 있습니다.");
        saveSubPage(ipAccessListPage);
    }

    private void makeMainPage() {
        ParentPageRequest.RequestInsert mainPage = new ParentPageRequest.RequestInsert();
        mainPage.setParentPageRootPath("/");
        mainPage.setParentPageTitle("메인 페이지");
        mainPage.setParentPageDescription("");
        Long parentPageId = saveParentPage(mainPage);

        SubPageRequest.RequestInsert indexPage = new SubPageRequest.RequestInsert();
        indexPage.setParentPageId(parentPageId);
        indexPage.setSubPagePath("/index");
        indexPage.setSubPageTitle("인덱스");
        saveSubPage(indexPage);
    }

    private void makePageAuthority() {
        PageAuthorityRequest.RequestInsert requestInsert = new PageAuthorityRequest.RequestInsert();
        requestInsert.setPageAuthorityCode(ROLE_ADMIN);
        requestInsert.setPageAuthorityName(ROLE_ADMIN);
        requestInsert.setPageAuthorityDescription("사이트 관리자입니다.");
        savePageAuthority(requestInsert);
    }

    private void saveSubPage(SubPageRequest.RequestInsert errorPage) {
        subPageRepository.save(subPageMapper.toInsertEntity(errorPage));
    }

    private Long saveParentPage(ParentPageRequest.RequestInsert errorParentPage) {
        return parentPageRepository.save(parentPageMapper.toInsertEntity(errorParentPage)).getParentPageId();
    }

    private void savePageAuthority(PageAuthorityRequest.RequestInsert requestInsert){
        pageAuthorityRepository.save(pageAuthorityMapper.toInsertEntity(requestInsert));
    }
}
