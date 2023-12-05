package baseline.version2.springboot.common.util;

import baseline.version2.springboot.common.util.response.ExceptionForm;
import baseline.version2.springboot.common.util.response.ResponseForm;
import baseline.version2.springboot.exceptionHandler.subType.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResponseUtil {

    /**
     *
     * @param contents
     * @return {
     *     "result" : true,
     *     "data" : {
     *         "contents" : contents,
     *         "pagination" : {
     *             "page" : page,
     *             "totalCount" : totalCount
     *         }
     *     }
     * }
     */

    public ResponseEntity<ResponseForm> makeResponseEntity(Page<?> page){

        return makeResponseEntity(page.getContent(), page.getNumber(), page.getTotalElements());
    }

    public ResponseEntity<ResponseForm> makeResponseEntity(Object contents, int page, Long totalCount){
        HashMap<String, Object> pagination = new HashMap<>();
        pagination.put("page", page + 1);
        pagination.put("totalCount", totalCount);

        HashMap<String, Object> data = new HashMap<>();
        data.put("contents", contents);
        data.put("pagination", pagination);

        ResponseForm responseForm = new ResponseForm(true, data);

        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

    /**
     *
     * @param result
     * @return {
     *     "result" : result
     * }
     */
    public ResponseEntity<ResponseForm> makeResponseEntity(boolean result){
        ResponseForm responseForm = new ResponseForm(result);

        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

    /**
     *
     * @return {
     *     "result" : true
     * }
     */
    public ResponseEntity<ResponseForm> makeResponseEntity(){
        return makeResponseEntity(true);
    }

    /**
     *
     * @param result
     * @param contents
     * @return {
     *     "result" : result,
     *     "data" : {
     *         "contents" : contents
     *     }
     * }
     */
    public ResponseEntity<ResponseForm> makeResponseEntity(boolean result, Object contents){
        HashMap<String, Object> data = new HashMap<>();
        data.put("contents", contents);
        ResponseForm responseForm = new ResponseForm(result, data);

        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

    /**
     *
     * @param contents
     * @return {
     *     "result" : true,
     *     "data" : {
     *         "contents" : contents
     *     }
     * }
     */
    public ResponseEntity<ResponseForm> makeResponseEntity(Object contents) {
        return makeResponseEntity(true, contents);
    }

    // -------------------------------------------------------------------------------------------------------------

    public ResponseEntity<ExceptionForm> makeExceptionEntity(Object errorMessage) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("errorMessage", errorMessage);
        ExceptionForm exceptionForm = new ExceptionForm(false, data);

        return new ResponseEntity<>(exceptionForm, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ExceptionForm> makeExceptionEntity(Object errorMessage, HttpStatus httpStatus) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("errorMessage", errorMessage);
        ExceptionForm exceptionForm = new ExceptionForm(false, data);

        return new ResponseEntity<>(exceptionForm, httpStatus);
    }

    public ResponseEntity<ExceptionForm> makeExceptionEntity(ServiceException serviceException){
        HashMap<String, Object> data = new HashMap<>();
        data.put("errorMessage", serviceException.getErrorMessage());
        ExceptionForm exceptionForm = new ExceptionForm(false, data);

        return new ResponseEntity<>(exceptionForm, serviceException.getStatusCode());
    }
}
