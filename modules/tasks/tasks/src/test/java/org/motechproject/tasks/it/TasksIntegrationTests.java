package org.motechproject.tasks.it;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({//AllChannelsIT.class, AllTaskDataProvidersIT.class, AllTasksIT.class,
                     TaskActivitiesDataServiceIT.class, TasksBundleIT.class})
public class TasksIntegrationTests {
}
