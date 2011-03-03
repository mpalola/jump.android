/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 Copyright (c) 2010, Janrain, Inc.

 All rights reserved.

 Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation and/or
   other materials provided with the distribution.
 * Neither the name of the Janrain, Inc. nor the names of its
   contributors may be used to endorse or promote products derived from this
   software without specific prior written permission.


 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.janrain.android.engage.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * An activity object you create, populate, and post to the user's activity stream.
 *
 * Create an activity object, fill in the object's fields, and pass the object to
 * the JREngage library when you are ready to publish. Currently supported providers are:
 *   - Facebook
 *   - LinkedIn
 *   - Twitter
 *   - MySpace
 *   - Yahoo!
 *
 * Janrain Engage will make a best effort to use all of the fields submitted in the activity
 * request, but note that how they get presented (and which ones are used) ultimately depends on
 * the provider.
 *
 * This API will work if and only if:
 *   - Your Janrain Engage application has been configured with the given provider
 *   - The user has already authenticated and has given consent to publish activity
 *
 * Otherwise, you will be given an error response indicating what was wrong. Detailed error
 * responses will also be given if the activity parameter does not meet the formatting requirements
 * described below.
 *
 * For more information of Janrain Engage's activity api, see <a
 * href="https://rpxnow.com/docs#api_activity">the activity section</a> of our API Documentation.
 */
public class JRActivityObject {

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    /**
     * A string describing what the user did, written in the third person (e.g.,
     * "wrote a restaurant review", "posted a comment", "took a quiz")
     */
    private String mAction;

    /** The URL of the resource being mentioned in the activity update */
    private String mUrl; //url associated with the action

    /**
     * A string containing user-supplied content, such as a comment or the first paragraph of
     * an article that the user wrote.
     *
     * NOTE: Some providers (Twitter in particular) may truncate this value.
     */
    private String mUserGeneratedContent;

    /**
     * The title of the resource being mentioned in the activity update.
     *
     * NOTE: No length restriction on the status is imposed by Janrain Engage, however Yahoo
     * truncates this value to 256 characters.
     */
    private String mTitle;

    /** A description of the resource mentioned in the activity update */
    private String mDescription;

    /**
     * An array of <code>JRActionLink</code> objects, each having two attributes: text and href.
     * An action link is a link a user can use to take action on an activity update on the provider
     * Example:
     * <code>
     * action_links:
     * [
     *   {
     *     "text": "Rate this quiz result",
     *     "href": "http://example.com/quiz/12345/result/6789/rate"
     *   },
     *   {
     *     "text": "Take this quiz",
     *     "href": "http://example.com/quiz/12345/take"
     *   }
     * ]
     * </code>
     *
     * NOTE: Any objects added to this array that are not of type <code>JRActionLink</code> will
     * be ignored.
     */
    private List<JRActionLink> mActionLinks = new ArrayList<JRActionLink>();

    /**
     * An array of objects with base class <code>JRMediaObject (i.e., JRImageMediaObject,
     * JRFlashMediaObject, JRMp3MediaObject)</code>.
     *
     * To publish attached media objects with your activity, create the preferred
     * object, populate the object's fields, then add the object to the <code>media</code> array.
     * You can attach pictures, videos, and mp3s to your activity, although how the
     * media objects get presented and whether or not they are used, depend on the provider.
     *
     * If you include more than one media type in the array, JREngage will
     * choose only one of these types, in this order:
     *   -# image
     *   -# flash
     *   -# mp3
     *
     * Also, any objects added to this array that are not of type <code>JRMediaObject</code>
     * will be ignored.
     *
     * Media object format and rules are identical to those described on the <a
     * href="http://developers.facebook.com/docs/guides/attachments">Facebook Developer page
     * on Attachments</a>.
     */
    private List<JRMediaObject> mMedia = new ArrayList<JRMediaObject>();

    /**
     * An object with attributes describing properties of the update. An attribute value can be
     * a string or an object with two attributes, text and href.
     * Example:
     * <code>
     *   properties:
     *   {
     *       "Time": "05:00",
     *       "Location":
     *       {
     *           "text": "Portland",
     *           "href": "http://en.wikipedia.org/wiki/Portland,_Oregon"
     *       }
     *   }
     * </code>
     */
    private Map<String, Object> mProperties = new HashMap<String, Object>();

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    /**
     * Returns a <code>JRActivityObject</code> initialized with the given action and url.
     *
     * @param action
     *   A string describing what the user did, written in the third person.  This value cannot
     *   be <code>null</code>.
     *
     * @param url
     *   The URL of the resource being mentioned in the activity update.  This value cannot
     *   be <code>null</code>.
     */
    public JRActivityObject(String action, String url) {
        mAction = action;
        mUrl = url;
    }

    // ------------------------------------------------------------------------
    // GETTERS/SETTERS
    // ------------------------------------------------------------------------

    public String getAction() {  /* (readonly) */
        return mAction;
    }

    public String getUrl() {  /* (readonly) */
        return mUrl;
    }

    public String getUserGeneratedContent() {
        return mUserGeneratedContent;
    }

    public void setUserGeneratedContent(String userGeneratedContent) {
        mUserGeneratedContent = userGeneratedContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<JRActionLink> getActionLinks() {
        return mActionLinks;
    }

    public void setActionLinks(List<JRActionLink> actionLinks) {
        mActionLinks = actionLinks;
    }

    public void setActionLink(JRActionLink al) {
        mActionLinks = new ArrayList<JRActionLink>();
        mActionLinks.add(al);
    }

    public List<JRMediaObject> getMedia() {
        return mMedia;
    }

    public void setMedia(List<JRMediaObject> media) {
        mMedia = media;
    }

    public void setMedia(JRMediaObject mo) {
        mMedia = new ArrayList<JRMediaObject>();
        mMedia.add(mo);
    }

    public Map<String, Object> getProperties() {
        return mProperties;
    }

    public void setProperties(Map<String, Object> properties) {
        mProperties = properties;
    }

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    /**
     * Returns a HashMap (Dictionary) representing the JRActivityObject.
     *
     * @return
     *   An HashMap (Dictionary) of String objects representing the JRActivityObject.
     *
     * NOTE: This function should not be used directly.  It is intended only for use by the
     * JREngage library.
     *
     * TODO: revisit visibility/usage
     */
    public Map<String, String> dictionaryForObject() {
        //todo store media object, action links, user generated content, description, title, text,
        //-> all of the things that comprise the fundament of an activity

        Map<String, String> map = new HashMap<String, String>();
        //should be a key here called action, i think this boils down into the parameters for rpxnow.com/.../activity
        map.put(mAction, mUrl);
        return map;
    }
}
