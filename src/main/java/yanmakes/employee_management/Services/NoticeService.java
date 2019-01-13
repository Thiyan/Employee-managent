package yanmakes.employee_management.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.NoticeRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.Notice;


import java.util.List;


@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Notice addNotice(Notice notice) throws EMException {

        try{
            notice=noticeRepository.save(notice);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return notice;
    }

    /**
     * THIS METHOD IS FOR ADDING A NEW NOTICE BY ADMIN
     * @return
     * @throws EMException
     */
    public List<Notice> getNotices() throws EMException {

        List<Notice> notices;
        try{
            notices=noticeRepository.findAll();
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(notices.isEmpty() || notices==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return notices;

    }

    /**
     * THIS METHOD IS FOR DELETING NEW NOTICE BY ADMIN
     * @param id
     * @return
     * @throws EMException
     */
    public Notice delete(String id) throws EMException {

        Notice notice;

        try {
            notice=noticeRepository.getOne((int) (Integer.valueOf(id)));
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }


        if (notice==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        try {
            noticeRepository.delete(notice);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        System.out.println(notice.toString());
        return notice;

    }

    /**
     * THIS METHOD IS FOR UPDATING A NOTICE BY ADMIN
     * @param notice
     * @return
     * @throws EMException
     */
    public Notice update(Notice notice) throws EMException {

        try {
            noticeRepository.getOne(notice.getnId());
        }catch (Exception ex){
            throw new EMException(EMStatus.ID_IS_REQUIRED);
        }


        try {
            notice=noticeRepository.save(notice);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return notice;

    }
}
