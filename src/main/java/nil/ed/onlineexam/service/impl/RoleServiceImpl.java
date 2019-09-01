package nil.ed.onlineexam.service.impl;

import nil.ed.onlineexam.aop.annotation.MethodInvokeLog;
import nil.ed.onlineexam.common.NormalResponseBuilder;
import nil.ed.onlineexam.common.PageResult;
import nil.ed.onlineexam.common.Response;
import nil.ed.onlineexam.common.ResponseCodeEnum;
import nil.ed.onlineexam.entity.Role;
import nil.ed.onlineexam.mapper.RoleMapper;
import nil.ed.onlineexam.service.IRoleService;
import nil.ed.onlineexam.service.support.DeleterHelper;
import nil.ed.onlineexam.service.support.SelectOneHelper;
import nil.ed.onlineexam.service.support.SelectPageHelper;
import nil.ed.onlineexam.service.support.UpdaterHelper;
import nil.ed.onlineexam.service.support.impl.SimpleSelectOneHelper;
import nil.ed.onlineexam.service.support.impl.SimpleSelectPageHelper;
import nil.ed.onlineexam.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.processing.RoundEnvironment;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.Executor;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;

    private DeleterHelper deleterHelper;

    private UpdaterHelper updaterHelper;

    private SelectOneHelper<Role> selectOneHelper = new SimpleSelectOneHelper<>();

    private Executor executor;

    @Autowired
    @Qualifier("simpleDeleteHelper")
    public void setDeleterHelper(DeleterHelper deleterHelper) {
        this.deleterHelper = deleterHelper;
    }


    @Autowired
    @Qualifier("simpleUpdateHelper")
    public void setUpdaterHelper(UpdaterHelper updaterHelper) {
        this.updaterHelper = updaterHelper;
    }

    @Autowired
    @Qualifier("commonExecutor")
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    @MethodInvokeLog
    public Response<Void> addRole(Role role, Integer creator) {
        setCreateFields(role, creator);

        roleMapper.insert(role);

        return new NormalResponseBuilder<Void>()
                .setCodeEnum(ResponseCodeEnum.SUCCESS)
                .build();
    }

    @Override
    @MethodInvokeLog
    public Response<Void> deleteRole(Integer roleId) {
        return deleterHelper.operate(() -> roleMapper.deleteRoleById(roleId));
    }

    @Override
    @MethodInvokeLog
    public Response<Role> getRoleById(Integer id) {
        return selectOneHelper.operate(() -> roleMapper.getRoleById(id));
    }

    @Override
    @MethodInvokeLog
    public Response<PageResult<Role>> listRolesByPage(Integer pageNo, Integer pageSize) {
        return new SimpleSelectPageHelper<Role>(executor)
                .setPageNo(pageNo)
                .setPageSize(pageSize)
                .setCounter(() -> roleMapper.countRoles())
                .operate(() -> roleMapper.listRoles(PageUtils.calPageStart(pageNo, pageSize), pageSize));
    }

    private void setCreateFields(Role role, Integer creator){
        role.setCreateTime(Instant.now().toEpochMilli());
        role.setCreator(creator);
        role.setLastUpdateTime(role.getCreateTime());
    }

    private void setUpdateFields(Role role, Integer updater){
        role.setLastUpdateTime(Instant.now().toEpochMilli());
    }
}