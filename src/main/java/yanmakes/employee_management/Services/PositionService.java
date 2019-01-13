package yanmakes.employee_management.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yanmakes.employee_management.DAO.PositionRepository;
import yanmakes.employee_management.Exceptions.EMException;
import yanmakes.employee_management.Exceptions.EMStatus;
import yanmakes.employee_management.models.Position;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Position addPosition(Position position) throws EMException {

        try{
            position=positionRepository.save(position);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return position;
    }


    /**
     * THIS METHOD IS FOR GET ALL A POSITIONS BY ADMIN
     * @return
     * @throws EMException
     */
    public List<Position> getPostions() throws EMException {

        List<Position> positions;
        try{
            positions=positionRepository.findAll();
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        if(positions.isEmpty() || positions==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        return positions;

    }

    /**
     * THIS METHOD IS FOR DELETING A POSITION BY ADMIN
     * @param id
     * @return
     * @throws EMException
     */
    public Position delete(String id) throws EMException {

        Position position;

        try {
            position=positionRepository.getOne((int) (Integer.valueOf(id)));
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }


        if (position==null)
            throw new EMException(EMStatus.NO_ENTRY_FOUND);

        try {
            positionRepository.delete(position);
        }catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        System.out.println(position.toString());
        return position;

    }

    /**
     * THIS METHOD IS FOR UPDATING A POSITION BY ADMIN
     * @param position
     * @return
     * @throws EMException
     */
    public Position update(Position position) throws EMException {

        try {
            positionRepository.getOne(position.getpId());
        }catch (Exception ex){
            throw new EMException(EMStatus.ID_IS_REQUIRED);
        }


        try {
            position=positionRepository.save(position);
        }
        catch (Exception ex){
            throw new EMException(EMStatus.DB_ERROR);
        }

        return position;
    }
}