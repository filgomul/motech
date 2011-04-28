/**
 * MOTECH PLATFORM OPENSOURCE LICENSE AGREEMENT
 *
 * Copyright (c) 2011 Grameen Foundation USA.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Grameen Foundation USA, nor its respective contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY GRAMEEN FOUNDATION USA AND ITS CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL GRAMEEN FOUNDATION USA OR ITS CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 */

package org.motechproject.outbox.web;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.motechproject.outbox.model.OutboundVoiceMessage;
import org.motechproject.outbox.model.VoiceMessageType;
import org.motechproject.server.outbox.service.VoiceOutboxService;
import org.springframework.web.servlet.ModelAndView;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VxmlControllerTest {

    @InjectMocks
    VxmlController vxmlController = new VxmlController();

    @Mock
    private VoiceOutboxService voiceOutboxService;

    @Mock
    private HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void initMocks() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOutboxMessage() {

        String outboundVoiceMessageTypeName = "appointmentReminder";
        OutboundVoiceMessage outboundVoiceMessage = new OutboundVoiceMessage();

        VoiceMessageType voiceMessageType = new VoiceMessageType();
        voiceMessageType.setVoiceMessageTypeName(outboundVoiceMessageTypeName);

        outboundVoiceMessage.setVoiceMessageType(voiceMessageType);

        when(voiceOutboxService.getNextPendingMessage(anyString())).thenReturn(outboundVoiceMessage);

        ModelAndView modelAndView = vxmlController.outboxMessage(request, response);

        Assert.assertEquals(outboundVoiceMessageTypeName, modelAndView.getViewName());

    }

    @Test
    public void testOutboxMessageWithMessageId() {

        String outboundVoiceMessageTypeName = "appointmentReminder";
        OutboundVoiceMessage outboundVoiceMessage = new OutboundVoiceMessage();

        VoiceMessageType voiceMessageType = new VoiceMessageType();
        voiceMessageType.setVoiceMessageTypeName(outboundVoiceMessageTypeName);

        outboundVoiceMessage.setVoiceMessageType(voiceMessageType);

        when(voiceOutboxService.getNextPendingMessage(anyString())).thenReturn(outboundVoiceMessage);
        when(request.getParameter("mId")).thenReturn("1");

        ModelAndView modelAndView = vxmlController.outboxMessage(request, response);

        Assert.assertEquals(outboundVoiceMessageTypeName, modelAndView.getViewName());

    }

    @Test
    public void testOutboxMessageNoMessage() {

        when(voiceOutboxService.getNextPendingMessage(anyString())).thenReturn(null);

        ModelAndView modelAndView = vxmlController.outboxMessage(request, response);

        Assert.assertEquals(VxmlController.NO_MESSAGE_TEMPLATE_NAME, modelAndView.getViewName());

    }

    @Test
    public void testOutboxMessageException() {

        String outboundVoiceMessageTypeName = "appointmentReminder";
        OutboundVoiceMessage outboundVoiceMessage = new OutboundVoiceMessage();

        VoiceMessageType voiceMessageType = new VoiceMessageType();
        voiceMessageType.setVoiceMessageTypeName(outboundVoiceMessageTypeName);

        outboundVoiceMessage.setVoiceMessageType(voiceMessageType);

        when(voiceOutboxService.getNextPendingMessage(anyString())).thenThrow(new RuntimeException());

        ModelAndView modelAndView = vxmlController.outboxMessage(request, response);

        Assert.assertEquals(VxmlController.ERROR_MESSAGE_TEMPLATE_NAME, modelAndView.getViewName());

    }

     @Test
    public void testOutboxMessageWithMessageIdException() {

        String outboundVoiceMessageTypeName = "appointmentReminder";
        OutboundVoiceMessage outboundVoiceMessage = new OutboundVoiceMessage();

        VoiceMessageType voiceMessageType = new VoiceMessageType();
        voiceMessageType.setVoiceMessageTypeName(outboundVoiceMessageTypeName);

        outboundVoiceMessage.setVoiceMessageType(voiceMessageType);

        when(voiceOutboxService.getNextPendingMessage(anyString())).thenThrow(new RuntimeException());
        when(request.getParameter("mId")).thenReturn("1");

        ModelAndView modelAndView = vxmlController.outboxMessage(request, response);

        Assert.assertEquals(VxmlController.ERROR_MESSAGE_TEMPLATE_NAME, modelAndView.getViewName());

    }

}
