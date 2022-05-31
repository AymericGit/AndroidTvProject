package fr.supinternet.androidtv.fragment


import android.os.Bundle
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction

class GuidedFragment : GuidedStepSupportFragment() {
    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance("Ma question", "Des détails", "Question 1", null)
    }

    override fun onSubGuidedActionClicked(action: GuidedAction?): Boolean {
        super.onSubGuidedActionClicked(action)
        val position = findActionPositionById(1)
        notifyActionChanged(position)
        return true
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        val actionOui = GuidedAction.Builder(requireContext()).id(0).title("Oui").build()
        val actionHesite = GuidedAction.Builder(requireContext()).id(1).title("J'hésite").description("Cliquez pour ouvrir").subActions(
            listOf(
                GuidedAction.Builder(requireContext()).id(2).title("Peut-être").build(),
                GuidedAction.Builder(requireContext()).id(3).title("Certainement").build()
            )
        ).build()
        val actionNon = GuidedAction.Builder(requireContext()).id(4).title("Non").build()
        actions.add(actionOui)
        actions.add(actionHesite)
        actions.add(actionNon)
    }


}