// Copyright 2023 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.classroom.model.Invitation;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.junit.Assert;
import org.junit.Test;

public class TestCreateInvitation extends BaseTest {

  private String userId = "insert_user_id";

  @Test
  public void testCreateInvitation() throws GeneralSecurityException, IOException {
    setup(CreateInvitation.SCOPES);
    Invitation invitation = CreateInvitation.createInvitation(testCourse.getId(), userId);
    Assert.assertNotNull("Invitation not created", invitation);
    Assert.assertEquals(invitation.getCourseId(), testCourse.getId());
    Assert.assertEquals(invitation.getUserId(), userId);
  }

  @Test
  public void testCreateInvitationWithInvalidCourseId()
      throws GeneralSecurityException, IOException {
    setup(CreateInvitation.SCOPES);
    Assert.assertThrows(
        GoogleJsonResponseException.class,
        () -> CreateInvitation.createInvitation("invalid-course-id", userId));
  }
}
