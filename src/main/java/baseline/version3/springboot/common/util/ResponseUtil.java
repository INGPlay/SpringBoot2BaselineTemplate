package baseline.version3.springboot.common.util;

import baseline.version3.springboot.common.domain.ExceptionForm;
import baseline.version3.springboot.common.domain.ResponseForm;
import baseline.version3.springboot.controllerAdvice.subType.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public final class ResponseUtil {

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

    public static ResponseEntity<ResponseForm> makeResponseEntity(Page<?> page){

        return makeResponseEntity(page.getContent(), page.getNumber(), page.getTotalElements());
    }

    public static ResponseEntity<ResponseForm> makeResponseEntity(Object contents, int page, Long totalCount){
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
    public static ResponseEntity<ResponseForm> makeResponseEntity(boolean result){
        ResponseForm responseForm = new ResponseForm(result);

        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

    /**
     *
     * @return {
     *     "result" : true
     * }
     */
    public static ResponseEntity<ResponseForm> makeResponseEntity(){
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
    public static ResponseEntity<ResponseForm> makeResponseEntity(boolean result, Object contents){
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
    public static ResponseEntity<ResponseForm> makeResponseEntity(Object contents) {
        return makeResponseEntity(true, contents);
    }

    // -------------------------------------------------------------------------------------------------------------

    public static ResponseEntity<ExceptionForm> makeExceptionEntity(Object errorMessage) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("errorMessage", errorMessage);
        ExceptionForm exceptionForm = new ExceptionForm(false, data);

        return new ResponseEntity<>(exceptionForm, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ExceptionForm> makeExceptionEntity(Object errorMessage, HttpStatus httpStatus) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("errorMessage", errorMessage);
        ExceptionForm exceptionForm = new ExceptionForm(false, data);

        return new ResponseEntity<>(exceptionForm, httpStatus);
    }

    public static ResponseEntity<ExceptionForm> makeExceptionEntity(ServiceException serviceException){
        HashMap<String, Object> data = new HashMap<>();
        data.put("errorMessage", serviceException.getErrorMessage());
        ExceptionForm exceptionForm = new ExceptionForm(false, data);

        return new ResponseEntity<>(exceptionForm, serviceException.getStatusCode());
    }

    public static ResponseEntity<ExceptionForm> makeExceptionEntity(BindingResult bindingResult){
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        return makeExceptionEntity(errors, HttpStatus.BAD_REQUEST);
    }
}
