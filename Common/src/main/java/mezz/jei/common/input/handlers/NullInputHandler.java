package mezz.jei.common.input.handlers;

import mezz.jei.common.input.IInternalKeyMappings;
import mezz.jei.common.input.IUserInputHandler;
import mezz.jei.common.input.UserInput;
import net.minecraft.client.gui.screens.Screen;

import java.util.Optional;

public class NullInputHandler implements IUserInputHandler {
	public static final NullInputHandler INSTANCE = new NullInputHandler();

	private NullInputHandler() {

	}

	@Override
	public Optional<IUserInputHandler> handleUserInput(Screen screen, UserInput input, IInternalKeyMappings keyBindings) {
		return Optional.empty();
	}
}
