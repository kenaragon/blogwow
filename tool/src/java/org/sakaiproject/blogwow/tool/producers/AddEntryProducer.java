package org.sakaiproject.blogwow.tool.producers;

import java.util.ArrayList;
import java.util.List;

import org.sakaiproject.blogwow.model.constants.BlogConstants;
import org.sakaiproject.blogwow.tool.params.BlogParams;

import uk.org.ponder.messageutil.MessageLocator;
import uk.org.ponder.rsf.components.UICommand;
import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIMessage;
import uk.org.ponder.rsf.components.UISelect;
import uk.org.ponder.rsf.components.UISelectChoice;
import uk.org.ponder.rsf.components.UIVerbatim;
import uk.org.ponder.rsf.components.decorators.DecoratorList;
import uk.org.ponder.rsf.components.decorators.UILabelTargetDecorator;
import uk.org.ponder.rsf.evolvers.TextInputEvolver;
import uk.org.ponder.rsf.flow.jsfnav.NavigationCase;
import uk.org.ponder.rsf.flow.jsfnav.NavigationCaseReporter;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.SimpleViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParamsReporter;

public class AddEntryProducer implements 
ViewComponentProducer,
ViewParamsReporter,
NavigationCaseReporter
{
    public static final String VIEWID = "add_entry";

    private NavBarRenderer navBarRenderer;
    private TextInputEvolver richTextEvolver;
    private MessageLocator messageLocator;

    public String getViewID() {
        return VIEWID;
    }

    public void fillComponents(UIContainer tofill, ViewParameters viewparams, ComponentChecker checker) {
        BlogParams params = (BlogParams) viewparams;

        String OTPid;
        boolean newentry = false;
        try {
            OTPid = new Long(params.blogid).toString();
        } 
        catch (Exception e) {
            OTPid = "NEW";
            newentry = true;
        }

        navBarRenderer.makeNavBar(tofill, "navIntraTool:", VIEWID);

        if (newentry)
            UIMessage.make(tofill, "add-entry-header", "blogwow.add_edit.addheader");
        else
            UIMessage.make(tofill, "add-entry-header", "blogwow.add_edit.editheader");

        UIForm form = UIForm.make(tofill, "edit-blog-entry-form");

        UIMessage.make(form, "title-label", "blogwow.add_edit.title");
        UIInput.make(form, "title-input", "EntryLocator."+OTPid+".title");

        UIInput blogtext = UIInput.make(form, "blog-text-input:", "EntryLocator."+OTPid+".text");
        richTextEvolver.evolveTextInput(blogtext);

        UIMessage.make(form, "privacy-instructions", "blogwow.add_edit.accesstext");

        String [] privacyRadioValues = new String[] {BlogConstants.PRIVACY_GROUP_LEADER,BlogConstants.PRIVACY_GROUP,BlogConstants.PRIVACY_PUBLIC};
        String [] privacyRadioLabelKeys = new String[] {"blogwow.add_edit.private","blogwow.add_edit.sitemembers","blogwow.add_edit.public"};

        UISelect privacyRadios = UISelect.make(form, "privacy-radio-holder", 
                privacyRadioValues, privacyRadioLabelKeys, "EntryLocator."+OTPid+".privacySetting", BlogConstants.PRIVACY_PUBLIC).setMessageKeys();

        String selectID = privacyRadios.getFullID();
        UISelectChoice instructorsOnlyRadio = UISelectChoice.make(form, "instructors-only-radio", selectID, 0);
        UIVerbatim instructorsOnlyLabel = UIVerbatim.make(form, "instructors-only-label", messageLocator.getMessage("blogwow.add_edit.private"));
        instructorsOnlyLabel.decorators = new DecoratorList(new UILabelTargetDecorator(instructorsOnlyRadio));

        UISelectChoice allMembersRadio = UISelectChoice.make(form, "all-members-radio", selectID, 1);
        UIVerbatim allMembersLabel = UIVerbatim.make(form, "all-members-label", messageLocator.getMessage("blogwow.add_edit.sitemembers"));
        allMembersLabel.decorators = new DecoratorList(new UILabelTargetDecorator(allMembersRadio));

        UISelectChoice publicViewableRadio = UISelectChoice.make(form, "public-viewable-radio", selectID, 2);
        UIVerbatim publicViewableLabel = UIVerbatim.make(form, "public-viewable-label", messageLocator.getMessage("blogwow.add_edit.public"));
        publicViewableLabel.decorators = new DecoratorList(new UILabelTargetDecorator(publicViewableRadio));

        UICommand.make(form, "publish-button", UIMessage.make("blogwow.add_edit.publish"), "EntryLocator.publishAll");
        UICommand.make(form, "save-button", UIMessage.make("blogwow.add_edit.save"), "EntryLocator.saveAll");
        UICommand.make(form, "cancel-button", UIMessage.make("blogwow.add_edit.cancel"), "EntryLocator.cancelAll");
    }

    public ViewParameters getViewParameters() {
        return new BlogParams();
    }

    public List reportNavigationCases() {
        List<NavigationCase> l = new ArrayList<NavigationCase>();
        l.add(new NavigationCase(null, new SimpleViewParameters(HomeProducer.VIEWID)));
        return l;
    }

    public void setMessageLocator(MessageLocator messageLocator) {
        this.messageLocator = messageLocator;
    }

    public void setNavBarRenderer(NavBarRenderer navBarRenderer) {
        this.navBarRenderer = navBarRenderer;
    }

    public void setRichTextEvolver(TextInputEvolver richTextEvolver) {
        this.richTextEvolver = richTextEvolver;
    }

}